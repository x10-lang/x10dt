#ifndef __X10_LANG_LOCK_H
#define __X10_LANG_LOCK_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_IO_CUSTOMSERIALIZATION_H_NODEPS
#include <x10/io/CustomSerialization.h>
#undef X10_IO_CUSTOMSERIALIZATION_H_NODEPS
#define X10_LANG_LOCK__REENTRANTLOCK_H_NODEPS
#include <x10/lang/Lock__ReentrantLock.h>
#undef X10_LANG_LOCK__REENTRANTLOCK_H_NODEPS
namespace x10 { namespace compiler { 
class Embed;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace compiler { 
class NativeClass;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 

class Lock : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::io::CustomSerialization::itable<x10::lang::Lock > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::Lock > _itable_1;
    
    void _instance_init();
    
    x10::lang::Lock__ReentrantLock _Embed___NATIVE_FIELD__;
    x10aux::ref<x10::lang::Lock__ReentrantLock> FMGL(__NATIVE_FIELD__);
    
    void _constructor(x10aux::ref<x10::lang::Lock__ReentrantLock> id0);
    
    static x10aux::ref<x10::lang::Lock> _make(x10aux::ref<x10::lang::Lock__ReentrantLock> id0);
    
    void _constructor();
    
    static x10aux::ref<x10::lang::Lock> _make();
    
    virtual void lock();
    virtual x10_boolean tryLock();
    virtual void unlock();
    virtual x10_int getHoldCount();
    virtual x10aux::ref<x10::io::SerialData> serialize();
    void _constructor(x10aux::ref<x10::io::SerialData> id__131);
    
    static x10aux::ref<x10::lang::Lock> _make(x10aux::ref<x10::io::SerialData> id__131);
    
    virtual x10aux::ref<x10::lang::Lock> x10__lang__Lock____x10__lang__Lock__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_LOCK_H

namespace x10 { namespace lang { 
class Lock;
} } 

#ifndef X10_LANG_LOCK_H_NODEPS
#define X10_LANG_LOCK_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/io/CustomSerialization.h>
#include <x10/lang/Lock__ReentrantLock.h>
#include <x10/compiler/Embed.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#include <x10/io/SerialData.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/lang/String.h>
#include <x10/compiler/NativeClass.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_LANG_LOCK_H_GENERICS
#define X10_LANG_LOCK_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Lock::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Lock> this_ = new (memset(x10aux::alloc<x10::lang::Lock>(), 0, sizeof(x10::lang::Lock))) x10::lang::Lock();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_LOCK_H_GENERICS
#endif // __X10_LANG_LOCK_H_NODEPS

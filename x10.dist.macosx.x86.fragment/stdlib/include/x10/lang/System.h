#ifndef __X10_LANG_SYSTEM_H
#define __X10_LANG_SYSTEM_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace util { 
class Timer;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Thread;
} } 
namespace x10 { namespace lang { 
class InterruptedException;
} } 
namespace x10 { namespace lang { 

class System : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::lang::System> _make();
    
    static x10_long currentTimeMillis();
    static x10_long nanoTime();
    static void exit();
    static void setExitCode(x10_int exitCode);
    static x10_boolean sleep(x10_long millis);
    virtual x10aux::ref<x10::lang::System> x10__lang__System____x10__lang__System__this(
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
#endif // X10_LANG_SYSTEM_H

namespace x10 { namespace lang { 
class System;
} } 

#ifndef X10_LANG_SYSTEM_H_NODEPS
#define X10_LANG_SYSTEM_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Long.h>
#include <x10/util/Timer.h>
#include <x10/lang/Int.h>
#include <x10/compiler/Native.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Place.h>
#include <x10/lang/String.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Thread.h>
#include <x10/lang/InterruptedException.h>
#ifndef X10_LANG_SYSTEM_H_GENERICS
#define X10_LANG_SYSTEM_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::System::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::System> this_ = new (memset(x10aux::alloc<x10::lang::System>(), 0, sizeof(x10::lang::System))) x10::lang::System();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_SYSTEM_H_GENERICS
#endif // __X10_LANG_SYSTEM_H_NODEPS

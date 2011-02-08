#ifndef __X10_LANG_ACTIVITY__CLOCKPHASES_H
#define __X10_LANG_ACTIVITY__CLOCKPHASES_H

#include <x10rt.h>


#define X10_UTIL_HASHMAP_H_NODEPS
#include <x10/util/HashMap.h>
#undef X10_UTIL_HASHMAP_H_NODEPS
namespace x10 { namespace lang { 
class Clock;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace lang { 

class Activity__ClockPhases : public x10::util::HashMap<x10aux::ref<x10::lang::Clock>, x10_int>
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[4];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::util::Map<x10aux::ref<x10::lang::Clock>, x10_int>::itable<x10::lang::Activity__ClockPhases > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::Activity__ClockPhases > _itable_1;
    
    static x10::io::CustomSerialization::itable<x10::lang::Activity__ClockPhases > _itable_2;
    
    void _instance_init();
    
    static x10aux::ref<x10::lang::Activity__ClockPhases> make(x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Clock> > > clocks);
    virtual void next();
    virtual void resume();
    virtual void drop();
    virtual x10aux::ref<x10::io::SerialData> serialize();
    void _constructor();
    
    static x10aux::ref<x10::lang::Activity__ClockPhases> _make();
    
    void _constructor(x10aux::ref<x10::io::SerialData> a);
    
    static x10aux::ref<x10::lang::Activity__ClockPhases> _make(x10aux::ref<x10::io::SerialData> a);
    
    virtual x10aux::ref<x10::lang::Activity__ClockPhases> x10__lang__Activity__ClockPhases____x10__lang__Activity__ClockPhases__this(
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
#endif // X10_LANG_ACTIVITY__CLOCKPHASES_H

namespace x10 { namespace lang { 
class Activity__ClockPhases;
} } 

#ifndef X10_LANG_ACTIVITY__CLOCKPHASES_H_NODEPS
#define X10_LANG_ACTIVITY__CLOCKPHASES_H_NODEPS
#include <x10/util/HashMap.h>
#include <x10/lang/Clock.h>
#include <x10/lang/Int.h>
#include <x10/array/Array.h>
#include <x10/lang/Iterator.h>
#include <x10/io/SerialData.h>
#ifndef X10_LANG_ACTIVITY__CLOCKPHASES_H_GENERICS
#define X10_LANG_ACTIVITY__CLOCKPHASES_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Activity__ClockPhases::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Activity__ClockPhases> this_ = new (memset(x10aux::alloc<x10::lang::Activity__ClockPhases>(), 0, sizeof(x10::lang::Activity__ClockPhases))) x10::lang::Activity__ClockPhases();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_ACTIVITY__CLOCKPHASES_H_GENERICS
#endif // __X10_LANG_ACTIVITY__CLOCKPHASES_H_NODEPS
